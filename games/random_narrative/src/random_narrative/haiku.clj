(ns random_narrative.haiku)

(defn- first_sentence [] 
  (rand-nth ["Snow in my show", "Summer's blaze"]))

(defn- second_sentence [] 
  (rand-nth ["Abandoned", "Full"]))

(defn- third_sentence [] 
  (rand-nth ["Sparrow's nest", "Moon"]))

(defn generate []
  [(first_sentence) (second_sentence) (third_sentence)])

