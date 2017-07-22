import tensorflow as tf

print "Constant nodes"
node1 = tf.constant(3.0, dtype=tf.float32)
node2 = tf.constant(4.0)
print node1, node2

print "\nEvaluating nodes"
sess = tf.Session()
print sess.run([node1, node2])

print "\nAdding nodes"
node3 = tf.add(node1, node2)
print node3
print sess.run(node3)

print "\nAdding placeholders"
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)
adder_node = a + b
print sess.run(adder_node, {a: 3, b:4.5})
print sess.run(adder_node, {a: [1,3], b: [2, 4]})

print "\nAdding and tripling"
add_and_triple = adder_node * 3
print sess.run(add_and_triple, {a: 3, b:4.5})

print "\nVariables"
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
linear_model = W * x + b

init = tf.global_variables_initializer()
sess.run(init)
print sess.run(linear_model, {x:[1,2,3,4]})

print "\nLoss function"
y = tf.placeholder(tf.float32)
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)
print sess.run(loss, {x:[1,2,3,4], y:[0,-1,-2,-3]})

print "\nReassign vars"
fixW = tf.assign(W, [-1.])
fixb = tf.assign(b, [1.])
sess.run([fixW, fixb])
print sess.run(loss, {x:[1,2,3,4], y:[0,-1,-2,-3]})

print "\nOptimizer"
sess.run(init)

optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)

for i in range(1000):
  sess.run(train, {x:[1,2,3,4], y:[0,-1,-2,-3]})

print sess.run([W, b])
print sess.run(loss, {x:[1,2,3,4], y:[0,-1,-2,-3]})
