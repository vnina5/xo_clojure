(ns xo-client.console.player-move
  (:require [xo-server.game-handler :as game_handler]
            [xo-client.console.input-output :as io]))

(defn make-player-move
  [game-state initial-data]
  (let [player (:player game-state)
        valid-moves (:valid-moves initial-data)]
    (loop [move (io/get-move player)]
      (cond
        (game_handler/is-move-invalid? valid-moves move)
          (recur (io/get-move-if-move-is-invalid move player))
        (game_handler/has-move-been-taken? game-state valid-moves move)
          (recur (io/get-move-if-move-was-taken move player))
        :else
          move))))
