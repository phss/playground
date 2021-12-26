(ns problem5)

(def doorid "abc")

(defn md5 [s]
  (apply str
         (map (partial format "%02x")
              (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                         .reset
                         (.update (.getBytes s)))))))

(defn start-with-zeros? [s]
  (clojure.string/starts-with? s "00000"))

(defn sixth-char [s]
  (nth s 5))

(println "Running")
(println
 (->> (iterate inc 0)
      (map (partial str doorid))
      (map md5)
      (filter start-with-zeros?)
      (map sixth-char)
      (take 2)
      (clojure.string/join "")))

