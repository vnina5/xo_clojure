(ns user
  (:use [clojure.tools.namespace.repl :only [refresh]]))

(defn rerun
  []
  (refresh))
