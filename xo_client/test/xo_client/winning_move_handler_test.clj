(ns xo-client.winning-move-handler-test
  (:require [clojure.test :refer :all]
            [xo-client.winning-move-handler :refer :all]))

(def winning-moves-on-3x3
  winning-moves)

(deftest get-winning-moves-test
  (testing "when choosing a side length of 3"
    (is (= winning-moves-on-3x3
           (get-winning-moves :3))
        "it returns the winning moves for a 3x3 board"))
  (testing "when choosing the default size"
    (is (= winning-moves-on-3x3
           (get-winning-moves :default-size))
        "it returns the winning moves for a 3x3 board")))
