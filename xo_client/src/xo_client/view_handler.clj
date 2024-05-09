(ns xo-client.view-handler
  (:require [xo-server.game-handler :as game_handler]
            [xo-server.core :refer [join-lines]]))

(def tokens {:1 "1", :2 "2", :3 "3"
             :4 "4", :5 "5", :6 "6"
             :7 "7", :8 "8", :9 "9"
             :X "X", :O "O"})

(defn- create-row
  [game-state token1 token2 token3]
  (format " %s | %s | %s ",
          ((game_handler/token-at game-state token1) tokens),
          ((game_handler/token-at game-state token2) tokens),
          ((game_handler/token-at game-state token3) tokens)))

(defn create-view
  [game-state]
  (join-lines [(create-row game-state :1 :2 :3)
               "-----------"
               (create-row game-state :4 :5 :6)
               "-----------"
               (create-row game-state :7 :8 :9)]))

(defn get-view
  []
  create-view)
