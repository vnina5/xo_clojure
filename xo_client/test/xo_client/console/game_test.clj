(ns xo-client.console.game-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [xo-client.console.game :refer :all]))

(defn- play-game-output []
  (with-out-str
    (with-in-str "p\n1\n2\n5\n9\n6\n4\n3\n7\n8\n"
                 (play-game))))

(deftest play-game-test
  (testing "when a game is played that ends in a tie (P vs. P)"
    (is (= true
           (str/includes?
             (play-game-output)
             "XO game"))
        "it sets up the game")
    (is (= true
           (str/includes?
             (play-game-output)
             " X | O | X "))
        "it displays the board with the moves made")
    (is (= true
           (str/includes?
             (play-game-output)
             "tie"))
        "it displays that the game ended in a tie"))
  (testing "when a player wins (P vs. P)"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "p\n1\n4\n2\n5\n3\n"
                            (play-game)))
             "won"))
        "it displays that the player won"))
  (testing "when passing in the side length"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "p\n1\n4\n2\n5\n3\n"
                            (play-game 3)))
             " O | O | 6 "))
        "it displays the board with the moves made"))
  (testing "when playing against a computer"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "c\n1\n1\n2\n4\n"
                            (play-game)))
             "choose the move order"))
        "it allows the user to go 1st or 2nd")))

