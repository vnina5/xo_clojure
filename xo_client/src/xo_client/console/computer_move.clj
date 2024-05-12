(ns xo-client.console.computer-move
  (:require [xo-server.computer-move :as computer]
            [xo-client.console.input-output :as io]))

(defn make-computer-move
  [game-state initial-data]
  (io/display-computer-move-message)
  (computer/make-minimax-move game-state initial-data))
