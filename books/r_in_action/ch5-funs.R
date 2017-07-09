mydata <- matrix(rnorm(30), nrow=6)

# mean of rows
apply(mydata, 1, mean)

# mean of cols
apply(mydata, 2, mean)