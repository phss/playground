library(reshape2)

ID <- c(1, 1, 2, 2)
Time <- c(1, 2, 1, 2)
X1 <- c(5, 3, 6, 2)
X2 <- c(6, 5, 1, 4)

mydata <- data.frame(ID, Time, X1, X2)

# melt
md <- melt(mydata, id=c("ID", "Time"))

# cast
original <- dcast(md, ID+variable~Time)