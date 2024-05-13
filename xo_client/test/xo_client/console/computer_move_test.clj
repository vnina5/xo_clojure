(ns xo-client.console.computer-move-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [xo-client.console.computer-move :refer :all]
            [xo-client.valid-move-handler :as valid_move_handler]))

(def valid-moves
  (valid_move_handler/get-valid-moves :3))

(def initial-data
  {:valid-moves valid-moves})

(deftest have-computer-move-test
  (testing "when having the computer move"
    (with-out-str
      (is (= :3
             (make-computer-move
               {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}}, :player :X}
               initial-data))
          "it returns a move"))
    (is (= true
           (str/includes?
             (with-out-str
               (make-computer-move
                 {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}}, :player :X}
                 initial-data))
             "computer moved"))
        "it displays that the computer moved")))