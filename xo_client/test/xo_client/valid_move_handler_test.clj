(ns xo-client.valid-move-handler-test
  (:require [clojure.test :refer :all]
            [xo-client.valid-move-handler :refer :all]))

(def valid-moves-on-3x3
  valid-moves)

(deftest get-valid-moves-test
  (testing "when choosing a side length of 3"
    (is (= valid-moves-on-3x3
           (get-valid-moves :3))
        "it returns the valid moves for a 3x3 board"))
  (testing "when choosing the default size"
    (is (= valid-moves-on-3x3
           (get-valid-moves :default-size))
        "it returns the valid moves for a 3x3 board")))
