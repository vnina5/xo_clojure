(ns xo-client.console.game-input
  (:require [xo-client.console.input-output :as io]))

(def valid-game-modes #{:p :c})

(def valid-move-orders #{:1 :2})

(defn- is-input-invalid?
  [valid-values input]
  (not (contains? valid-values input)))

(defn- get-valid-input
  [valid-values get-game-input]
  (loop [input (io/get-initial-input)]
    (if (is-input-invalid? valid-values input)
      (recur (get-game-input input))
      input)))

(defn get-valid-game-mode
  []
  (get-valid-input valid-game-modes io/get-game-mode))

(defn get-valid-move-order
  []
  (get-valid-input valid-move-orders io/get-move-order))
