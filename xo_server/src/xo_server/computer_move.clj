(ns xo-server.computer-move
  (:require [xo-server.game-handler :as game_handler]
            [xo-server.minimax :as minimax]))

(defn make-random-move
  [game-state valid-moves]
  (let [available-moves (game_handler/get-available-moves
                          game-state valid-moves)]
    (rand-nth available-moves)))

(defn make-minimax-move
  [game-state initial-data]
  (minimax/minimax-move game-state initial-data))
