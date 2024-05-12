(ns xo-client.console.game
  (:require [xo-server.game-handler :as game_handler]
            [xo-client.console.game-setup :as game_setup]
            [xo-client.console.input-output :as io]))

(defn- int-to-keyword
  [num]
  (keyword (str num)))

(defn- get-side-length
  [args]
  (let [length (first args)]
    (if-not (nil? length)
      (int-to-keyword length)
      :default-size)))

(defn- get-starting-player
  [game-mode move-order]
  (if (and (= game-mode :computer)
           (= move-order :2))
    :O
    :X))

(defn- get-perform-setup
  [args]
  (let [side-length (get-side-length (flatten args))
        _ (io/display-introduction)
        game-mode (game_setup/get-game-mode)
        move-order (game_setup/get-move-order game-mode)
        starting-player (get-starting-player game-mode move-order)]
    { :side-length side-length
      :game-mode game-mode
      :starting-player starting-player }))


(defn- create-initial-data
  [{:keys [side-length game-mode]}]
  (let [valid-moves (game_setup/get-valid-moves side-length)
        winning-moves (game_setup/get-winning-moves side-length)
        move-strategies (game_setup/decide-strategies game-mode)
        create-view (game_setup/get-create-view)
        initial-data (game_handler/create-initial-data valid-moves winning-moves move-strategies create-view)]
    initial-data))

(defn- create-starting-game-state
  [{:keys [starting-player]}]
  (game_handler/create-game-state game_handler/empty-board starting-player false))

(defn- display-board
  [game-state {:keys [create-view]}]
  (io/display-board game-state create-view))


(defn- play-round
  [game-state initial-data]
  (let [get-move (game_handler/get-move-strategy game-state initial-data)
        move (get-move game-state initial-data)
        next-game-state (game_handler/add-move game-state initial-data move)
        _ (display-board next-game-state initial-data)]
    next-game-state))

(defn- play-all-rounds
  [starting-game-state initial-data]
  (loop [update-game-state (play-round starting-game-state initial-data)]
    (if (:finished? update-game-state)
      update-game-state
      (recur (play-round update-game-state initial-data)))))


(defn- display-instructions
  [{:keys [create-view]}]
  (game_setup/display-instructions create-view))

(defn- display-game-over-message
  [game-state {:keys [winning-moves]}]
  (let [winner (game_handler/get-winner game-state winning-moves)]
    (io/display-game-over-message winner)))


(defn play-game
  [& args]
  (let [setup-data (get-perform-setup args)
        initial-data (create-initial-data setup-data)
        starting-game-state (create-starting-game-state setup-data)
        _ (display-instructions initial-data)
        final-game-state (play-all-rounds starting-game-state initial-data)
        _ (display-game-over-message final-game-state initial-data)]))
