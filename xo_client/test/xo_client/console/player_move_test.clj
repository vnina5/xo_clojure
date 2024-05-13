(ns xo-client.console.player-move-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [xo-client.console.player-move :refer :all]
            [xo-client.valid-move-handler :as valid_move_handler]
            [xo-server.board :as board]))

(def valid-moves
  (valid_move_handler/get-valid-moves :3))

(def empty-game-state
  {:board board/empty-board, :player :X})

(def initial-data
  {:valid-moves valid-moves})

(deftest make-player-move-test
  (testing "when initially getting the move from the player"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "1"
                            (make-player-move empty-game-state initial-data)))
             "Player X, please enter"))
        "it displays the prompt"))
  (testing "when a valid move is entered"
    (with-out-str
      (is (= :2
             (with-in-str "2"
                          (make-player-move empty-game-state initial-data)))
          "it returns the move as a keyword")))
  (testing "when an invalid move is entered, followed by a valid move"
    (let [output (with-out-str
                   (with-in-str "x\n1"
                                (make-player-move empty-game-state initial-data)))]
      (is (= true
             (str/includes? output "is invalid"))
          "it displays the correct error message")
      (is (= 2
             (count (re-seq #"enter your move" output))))
      "it displays the prompt twice"))
  (testing "when a move is entered that has already been taken"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "3\n1"
                            (make-player-move {:board {:X #{:3}, :O #{}}, :player :O}
                                            initial-data)))
             "already taken"))
        "it displays the correct error message")))
