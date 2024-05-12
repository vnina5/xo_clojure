(ns xo-client.core
  (:require [xo-client.console.game :as game])
  (:gen-class))

(defn -main
  "Custom XO game."
  [& args]
  (game/play-game args))
