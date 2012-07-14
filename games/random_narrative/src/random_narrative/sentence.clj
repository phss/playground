; Ripped off from http://thinkzone.wlonk.com/PoemGen/PoemGen.htm
(ns random_narrative.sentence)

(defn adjective [] (rand-nth '(big small old fast cold hot dark dusty grimy dry)))

(defn concrete_noun [] (rand-nth '(street sidewalk corner door window hood slum skyscraper car truck)))

(defn adverb [] (rand-nth '(quickly loudly calmly quietly roughly)))

(defn transitive_verb [] (rand-nth '(get grab shove love desire buy sell fight hustle drive)))

(defn abstract_noun [] (rand-nth '(action work noise desolation death life love faith anger exhaustion)))

(defn- sentence []
  (rand-nth [
    (str "The " (adjective) " " (concrete_noun) " " (adverb) " " (transitive_verb) "s the " (concrete_noun) ".")
    (str (adverb) ", " (adjective) " " (concrete_noun) "s " (adverb) " " (transitive_verb) " a " (adjective) ", " (adjective) " " (concrete_noun) ".")
    (str (abstract_noun) " is a " (adjective) " " (concrete_noun) "!")
    (str (concrete_noun) "s " (abstract_noun) "!")
   ]))

  

(defn generate [] [(sentence) (sentence) (sentence) (sentence)])

