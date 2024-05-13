(ns xo-client.console.game-setup-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [xo-client.console.game-setup :refer :all]
            [xo-client.view-handler :as view_handler]
            [xo-client.valid-move-handler :as valid_move_handler]
            [xo-client.winning-move-handler :as winning_move_handler]
            [xo-client.console.computer-move :as computer_move]
            [xo-client.console.player-move :as player_move]))

(deftest get-game-mode-test
  (testing "when getting the game mode"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "p\n"
                            (get-game-mode)))
             "\"p\" to play"))
        "it displays the game mode instructions")
    (with-out-str
      (is (= :player
             (with-in-str "p\n"
                          (get-game-mode)))
          "it returns the game mode"))))

(deftest get-move-order-test
  (testing "when getting the move order, while playing a computer"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "1\n"
                            (get-move-order :computer)))
             "\"1\" to go first"))
        "it displays the move order instructions")
    (with-out-str
      (is (= :1
             (with-in-str "1\n"
                          (get-move-order :computer)))
          "it returns the move order")))
  (testing "when not playing a computer"
    (with-out-str
      (is (= nil
             (with-in-str "1\n"
                          (get-move-order :player)))
          "it returns nil"))))

(deftest get-valid-moves-test
  (testing "when a side length is passed in"
    (is (= valid_move_handler/valid-moves
           (get-valid-moves :3))
        "it returns the valid moves")))

(deftest get-winning-moves-test
  (testing "when the function is called"
    (is (= winning_move_handler/winning-moves
           (get-winning-moves :3))
        "it returns the winning moves")))


(deftest decide-strategies-2-test
  (testing "when the game is Player vs. Player"
    (is (= {:X player_move/make-player-move, :O player_move/make-player-move}
           (decide-strategies-2 :player nil))
        "it returns the strategies for 2 players players"))
  (testing "when the game is Player vs. Computer"
    (is (= {:X player_move/make-player-move, :O computer_move/make-computer-move}
           (decide-strategies-2 :computer :1))
        "it returns strategies for a player and a computer"))
  (testing "when the game is Computer vs. Player"
    (is (= {:X computer_move/make-computer-move, :O player_move/make-player-move}
           (decide-strategies-2 :computer :2))
        "it returns strategies for a computer and a player")))


(deftest get-create-view-test
  (testing "when getting the create view function"
    (is (= view_handler/create-view
           (get-create-view))
        "it returns the function")))

(deftest display-instructions-test
  (testing "when the game is set up"
    (is (= true
           (str/includes?
             (with-out-str
               (display-instructions view_handler/create-view))
             "type a number"))
        "it displays the instructions")
    (is (= true
           (str/includes?
             (with-out-str
               (display-instructions view_handler/create-view))
             " 4 | 5 | 6 "))
        "it displays the example board")))