
(def eyes [:wide :small :tilted :closed])
(def mouth [:grin :open :tiny])
(def shape [:round :square :oval])
(def colour [:red :green :blue])

(defn generate-face []
  {
   :shape (rand-nth shape)
   :colour (rand-nth colour)
   :eyes (rand-nth eyes)
   :mouth (rand-nth mouth)
  })

(println (generate-face))
