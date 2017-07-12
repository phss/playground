library(vcd)

counts <- table(Arthritis$Improved)

barplot(counts,
        main="Simple Bar Plot",
        xlab="Improvement", ylab="Frequency")

barplot(counts,
        main="Horizontal Bar Plot",
        xlab="Improvement", ylab="Frequency",
        horiz=T)

stackcounts <- table(Arthritis$Improved, Arthritis$Treatment)

barplot(stackcounts,
        main="Stacked Bar Plot",
        xlab="Improvement", ylab="Frequency",
        col=c("red", "yellow", "green"),
        legend=rownames(stackcounts))