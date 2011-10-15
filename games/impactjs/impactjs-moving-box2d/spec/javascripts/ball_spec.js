describe("Ball", function() {

  it("should be bouncy", function() {
    var ball = new EntityBall();

    expect(ball.restitution).toEqual(1);
    expect(ball.friction).toEqual(0);
  });
});