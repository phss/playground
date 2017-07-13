library(vcd)

t <- table(Arthritis$Improved)
t
prop.table(t)*100

t <- xtabs(~ Treatment + Improved, data=Arthritis)
t
prop.table(t,1)*100