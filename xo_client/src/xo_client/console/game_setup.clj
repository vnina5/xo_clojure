(ns xo-client.console.game-setup
  (:require [xo-server.game-handler :as game_handler]
            [xo-client.console.input-output :as io]
            [xo-client.console.game-input :as game_input]
            [xo-client.console.computer-move :as computer_move]
            [xo-client.console.player-move :as player_move]
            [xo-client.view-handler :as view-handler]
            [xo-client.valid-move-handler :as valid_move_handler]
            [xo-client.winning-move-handler :as winning_move_handler]))

;game mode
(def game-mode-mapping
  { :p :player,
    :c :computer })

(defn- convert-game-mode
  [user-game-mode]
  (user-game-mode game-mode-mapping))

(defn get-game-mode
  []
  (io/display-game-mode-instructions)
  (let [user-game-mode (game_input/get-valid-game-mode)
        internal-game-mode (convert-game-mode user-game-mode)]
    internal-game-mode))


;move order
(defn get-move-order
  [game-mode]
  (when (= game-mode :computer)
    (io/display-move-order-instructions)
    (game_input/get-valid-move-order)))


;valid moves
(defn get-valid-moves
  [side-length]
  (valid_move_handler/get-valid-moves side-length))

;winning moves
(defn get-winning-moves
  [side-length]
  (winning_move_handler/get-winning-moves side-length))


;strategies
(defn decide-strategies
  [game-mode]
  (cond
    (= game-mode :player)
      {:X player_move/make-player-move, :O player_move/make-player-move}
    (= game-mode :computer)
      {:X player_move/make-player-move, :O computer_move/make-computer-move}))


(defn get-create-view
  []
  view-handler/create-view)

(defn display-instructions
  [create-view]
  (io/display-game-instructions)
  (io/display-board game_handler/empty-board create-view))
