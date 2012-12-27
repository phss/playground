(ns rogue-clj.config)

(def cell {:width 12, :height 15})

(def cell-representation {:blank ".",
                          :wall "X",
                          :player "@",
                          :goblin "G"})
 
(def world-map ["WWWWWWWWWWWWWWWWWWWWWWWW"
                "W                      W"
                "W   W      W     W     W"
                "W      G               W"
                "W                   G  W"
                "W         P            W"
                "W   W            W     W"
                "W                      W"
                "WWWWWWWWWWWWWWWWWWWWWWWW"])

(def world-size {:width (count (world-map 0)), 
                 :height (count world-map)})
