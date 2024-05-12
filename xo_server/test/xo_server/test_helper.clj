(ns xo-server.test-helper)

(def valid-moves
  #{:1 :2 :3
    :4 :5 :6
    :7 :8 :9})


(def winning-rows
  (list #{:1 :2 :3}
        #{:4 :5 :6}
        #{:7 :8 :9}))

(def winning-columns
  (list #{:1 :4 :7}
        #{:2 :5 :8}
        #{:3 :6 :9}))

(def winning-diagonals
  (list #{:1 :5 :9}
        #{:3 :5 :7}))

(def winning-moves
  (concat winning-rows winning-columns winning-diagonals))
