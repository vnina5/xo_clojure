(ns xo-server.win-check
  (:require [clojure.set :as set]
            [xo-server.core :refer [contains-set?]]))

(defn did-player-win?
  [board player win-moves]
  (let [player-moves (player board)]
    (contains-set? win-moves player-moves)))

(defn which-player-won?
  [board win-moves]
  (cond
    (did-player-win? board :X win-moves) :X
    (did-player-win? board :O win-moves) :O
    :else false))

(defn did-either-player-win?
  [board win-moves]
  (boolean (which-player-won? board win-moves)))

(defn calculate-score
  [board win-moves]
  (let [winner (which-player-won? board win-moves)]
    (cond
      (= winner :X) 1
      (= winner :O) -1
      :else 0)))
