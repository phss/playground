describe("Player", function() {
  var player;

  beforeEach(function() {
    player = new EntityPlayer(10, 20);
  });


  it("should not move if no key is pressed", function() { 
    var previousX = player.pos.x,
        previousY = player.pos.y;
        
    whenPlayerUpdates();
    
    expect(player.pos.x).toEqual(previousX);
    expect(player.pos.y).toEqual(previousY);
  });

  it("should go up when up key is pressed", function() { 
    var previousX = player.pos.x,
        previousY = player.pos.y;

    givenKeyPressed("up");
    whenPlayerUpdates();
    
    expect(player.pos.x).toEqual(previousX);
    expect(player.pos.y).toBeLessThan(previousY);
  });

  it("should go down when down key is pressed", function() { 
    var previousX = player.pos.x,
        previousY = player.pos.y;

    givenKeyPressed("down");
    whenPlayerUpdates();
    
    expect(player.pos.x).toEqual(previousX);
    expect(player.pos.y).toBeGreaterThan(previousY);
  });

  it("should go left when left key is pressed", function() { 
    var previousX = player.pos.x,
        previousY = player.pos.y;

    givenKeyPressed("left");
    whenPlayerUpdates();
    
    expect(player.pos.x).toBeLessThan(previousX);
    expect(player.pos.y).toEqual(previousY);
  });

  it("should go right when right key is pressed", function() { 
    var previousX = player.pos.x,
        previousY = player.pos.y;

    givenKeyPressed("right");
    whenPlayerUpdates();
    
    expect(player.pos.x).toBeGreaterThan(previousX);
    expect(player.pos.y).toEqual(previousY);
  });

  function givenKeyPressed(key) {
    ig.input.actions[key] = true;
  };

  function whenPlayerUpdates() {
    player.update();
    ig.world.Step( 300, 5 );
    player.update();
  }


});