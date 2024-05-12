(ns xo-server.win-check-test
  (:require [clojure.test :refer :all]
            [xo-server.board :as board]
            [xo-server.win-check :refer :all]
            [xo-server.test-helper :as test_helper]))

(def winning-moves
  test_helper/winning-moves)

(deftest did-player-win?-test
  (testing "when the player won by completing the top row"
    (is (= true
           (did-player-win? {:X #{:1 :2 :3}, :O #{:4 :6}} :X winning-moves))
        "it returns true"))
  (testing "when the player didn't win"
    (is (= false
           (did-player-win? board/empty-board :X winning-moves))
        "it returns false")))

(deftest which-player-won?-test
  (testing "when the game just started"
    (is (= false
           (which-player-won? board/empty-board winning-moves))
        "it returns false, since neither player won"))
  (testing "when Player X won"
    (is (= :X
           (which-player-won? {:X #{:1 :4 :7}, :O #{:5 :6}} winning-moves))
        "it returns X's keyword")))

(deftest did-either-player-win?-test
  (testing "when a player won"
    (is (= true
           (did-either-player-win? {:X #{:1 :2 :3}, :O #{}} winning-moves))
        "it returns true"))
  (testing "when neither player won"
    (is (= false
           (did-either-player-win? board/empty-board winning-moves))
        "it returns false")))

(deftest calculate-score-test
  (testing "when Player X won"
    (is (= 1
           (calculate-score {:X #{:1 :2 :3} :O #{}} winning-moves))
        "it returns the score for X winning"))
  (testing "when Player O won"
    (is (= -1
           (calculate-score {:X #{} :O #{:1 :2 :3}} winning-moves))
        "it returns the score for O winning"))
  (testing "when the game resulted in a draw"
    (is (= 0
           (calculate-score {:X #{:1 :3 :5 :6 :8}, :O #{:2 :4 :7 :9}} winning-moves))
        "it returns the draw score")))

