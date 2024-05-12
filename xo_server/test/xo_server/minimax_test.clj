(ns xo-server.minimax-test
  (:require [clojure.test :refer :all]
            [xo-server.test-helper :as test_helper]
            [xo-server.minimax :refer :all]))


(def valid-moves
  test_helper/valid-moves)

(def winning-moves
  test_helper/winning-moves)

(def initial-data
  {:valid-moves valid-moves
   :winning-moves winning-moves})

(deftest minimax-test
  (testing "when Player X already won"
    (let [score-info (minimax
                       {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
                        :player :O, :finished? true}
                       initial-data)]
      (is (= 1
             (:score score-info))
          "it returns the score for X winning")
      (is (= :invalid-move
             (:move score-info))
          "it returns an invalid move")
      (is (= 5
             (:total-moves score-info))
          "it returns the total number of moves made")))
  (testing "when Player O won"
    (is (= -1
           (:score (minimax
                     {:board {:X #{:4 :7 :8}, :O #{:1 :2 :3}},
                      :player :X, :finished? true}
                     initial-data)))
        "it returns the score for O winning"))
  (testing "when the game resulted in a draw"
    (is (= 0
           (:score (minimax
                     {:board {:X #{:1 :5 :6 :3 :8}, :O #{:2 :9 :4 :7}},
                      :player :O, :finished? true}
                     initial-data)))
        "it returns the draw score"))
  (testing "when Player X can win now (1 move left)"
    (let [score-info (minimax
                       {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}},
                        :player :X, :finished? false}
                       initial-data)]
      (is (= 1
             (:score score-info))
          "it returns the score for X winning")
      (is (= :3
             (:move score-info))
          "it returns the move to win")
      (is (= 9
             (:total-moves score-info))
          "it returns 8 for the total number of moves")))
  (testing "when Player O can win now (2 moves left)"
    (let [score-info (minimax
                       {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
                        :player :O, :finished? false}
                       initial-data)]
      (is (= -1
             (:score score-info))
          "it returns the score for O winning")
      (is (= :6
             (:move score-info))
          "it returns the move to win")))
  (testing "when Player O cannot win (2 moves left)"
    (is (= :9
           (:move (minimax
                    {:board {:X #{:1 :7 :6 :8}, :O #{:4 :5 :2}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to force a draw"))
  (testing "when Player O determines that it cannot win"
    (is (= :4
           (:move (minimax
                    {:board {:X #{:1 :7}, :O #{:5}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to eventually draw"))
  (testing "when Player O can win now (3 moves left)"
    (is (= :7
           (:move (minimax
                    {:board {:X #{:1 :9 :2}, :O #{:6 :3 :5}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to win")))

(deftest minimax-move-test
  (testing "when Player O can win now (2 moves left)"
    (is (= :6
           (minimax-move
             {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
              :player :O, :finished? false}
             initial-data))
        "it returns the move to win")))

