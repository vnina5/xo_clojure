(ns xo-client.console.game-input-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [xo-client.console.game-input :refer :all]))

(deftest get-valid-game-mode-test
  (testing "when initially getting the game mode"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "p\n"
                            (get-valid-game-mode)))
             ">"))
        "it displays the angle bracket"))
  (testing "when a valid game mode is entered"
    (with-out-str
      (is (= :p
             (with-in-str "p\n"
                          (get-valid-game-mode)))
          "it returns the game mode as a keyword")))
  (testing "when an invalid mode is entered 2 times, followed by a valid mode"
    (let [output (with-out-str
                   (with-in-str "X\nX\np\n"
                                (get-valid-game-mode)))]
      (is (= true
             (str/includes? output "is invalid"))
          "it displays the correct error message")
      (is (= true
             (str/includes? output "enter the game mode"))
          "it displays the game mode prompt")
      (is (= 2
             (count (re-seq #"enter the game mode" output)))
          "it displays the game mode prompt twice"))))

(deftest get-valid-move-order-test
  (testing "when a valid move order is entered"
    (with-out-str
      (is (= :1
             (with-in-str "1\n"
                          (get-valid-move-order)))
          "it returns the move order as a keyword"))))