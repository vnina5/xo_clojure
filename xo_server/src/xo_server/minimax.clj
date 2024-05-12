(ns xo-server.minimax
  (:require [xo-server.game-handler :as game_handler]
            [xo-server.win-check :as win_check]))


(declare minimax)

; score info with move
;create raw score info list
(defn- create-new-game-states
  [game-state initial-data moves]
  (map #(game_handler/add-move game-state initial-data %) moves))

(defn- create-raw-score-info-list
  [game-state initial-data moves]
  (let [new-game-states (create-new-game-states game-state initial-data moves)
        raw-score-info-list (map #(minimax % initial-data) new-game-states)]
    raw-score-info-list))

;get-score-based-on-player
(defn- max-or-min-based-on-player
  [player]
  (cond
    (= player :X) max
    (= player :O) min))

(defn- get-score-based-on-player
  [scores game-state]
  (let [player (:player game-state)]
    (apply (max-or-min-based-on-player player) scores)))

;get optimal move
(defn- get-maps-with-target-score
  [score-info-list score]
  (filter #(= score (:score %)) score-info-list))

(defn- sort-maps-by-total-moves
  [maps]
  (sort-by :total-moves (vec maps)))

(defn- extract-move-from-first-map
  [sorted-maps]
  (:move (first sorted-maps)))

(defn- get-optimal-move
  [score-info-list score]
  (let [maps-with-target-score (get-maps-with-target-score score-info-list score)
        sorted-maps (sort-maps-by-total-moves maps-with-target-score)
        move (extract-move-from-first-map sorted-maps)]
    move))

;score info with move
(defn- get-scores
  [score-info-list]
  (map #(:score %) score-info-list))

(defn- get-totals
  [score-info-list]
  (map #(:total-moves %) score-info-list))

(defn- create-score-info-list
  [scores moves total-moves]
  (map #(assoc {} :score %1 :move %2 :total-moves %3)
       scores moves total-moves))


;minimax
(defn- score-info-without-move
  [game-state {:keys [winning-moves]}]
  (let [score (game_handler/calculate-score game-state winning-moves)
        total-moves (game_handler/get-total-moves game-state)]
    {:score score, :move :invalid-move, :total-moves total-moves}))

(defn- score-info-with-move
  [game-state {:keys [valid-moves] :as initial-data}]
  (let [moves (game_handler/get-available-moves game-state valid-moves)
        raw-score-info-list (create-raw-score-info-list game-state initial-data moves)
        scores (get-scores raw-score-info-list)
        totals (get-totals raw-score-info-list)
        score-info-list (create-score-info-list scores moves totals)
        score (get-score-based-on-player scores game-state)
        move (get-optimal-move score-info-list score)
        total-moves (first totals)]
    {:score score, :move move, :total-moves total-moves}))

(def minimax
  (memoize
    (fn [game-state initial-data]
      (if (:finished? game-state)
        (score-info-without-move game-state initial-data)
        (score-info-with-move game-state initial-data)))))

; minimax-move function
(defn minimax-move
  [game-state initial-data]
  (:move (minimax game-state initial-data)))

