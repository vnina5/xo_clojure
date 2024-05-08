(ns xo-client.core
  (:require [xo-client.console.console :as console])
  (:gen-class))

(defn -main
  "Custom XO game."
  [& args]
  (console/play-game args))
