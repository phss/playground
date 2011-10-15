describe("Player", function() {
  it("should kind of work", function() { 
    var boundingBox = new b2.AABB();
    boundingBox.lowerBound.Set( 0, 0 );
    boundingBox.upperBound.Set( 1024, 512);
    var gravity = new b2.Vec2( 0, 30 );
    ig.world = new b2.World( boundingBox, gravity, true );

    var p = new EntityPlayer(10, 20);
    
    expect(p.friction).toEqual(0);
  });
});