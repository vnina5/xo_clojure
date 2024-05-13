(ns xo-client.view-handler-test
  (:require [clojure.test :refer :all]
            [xo-client.view-handler :refer :all]
            [xo-server.game-handler :as game_handler]
            [xo-server.core :refer [join-lines]]))


(deftest create-view-test
  (testing "when an empty board is passed in"
    (let [view (join-lines [""
                            " 1 | 2 | 3 "
                            "-----------"
                            " 4 | 5 | 6 "
                            "-----------"
                            " 7 | 8 | 9 "])]
      (is (= view
             (create-view game_handler/empty-board))
          "it returns the formatted string for the example board")))
  (testing "when a game state with a board is passed in"
    (let [view (join-lines [""
                            " X | 2 | X "
                            "-----------"
                            " 4 | O | 6 "
                            "-----------"
                            " 7 | 8 | 9 " ])]
      (is (= view
             (create-view {:board {:X #{:1 :3}, :O #{:5}}}))
          "it returns the formatted string for the board"))))


(deftest get-create-view-test
  (testing "when getting the create view function"
    (is (= create-view
           (get-view))
        "it returns the function")))
