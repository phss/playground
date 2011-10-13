ig.module(
  'game.entities.ball'
)
.requires(
  'impact.entity'
)
.defines(function(){

EntityBall = ig.Entity.extend({
  
  size: { x: 5, y: 5 },  
  collides: ig.Entity.COLLIDES.ACTIVE, 
  color: '#f0f',
  bounciness: 1,
  
  init: function( x, y, settings ) {
    // this.addAnim( 'idle', 1, [0] );
    
    this.vel.x = -100;
    this.vel.y = 200;

    this.parent( x, y, settings );
  },

  draw: function() {
    var ctx = ig.system.context;
    var s = ig.system.scale;
    var x = this.pos.x * s - ig.game.screen.x * s;
    var y = this.pos.y * s - ig.game.screen.y * s;
    var sizeX = this.size.x * s;
    var sizeY = this.size.y * s;
    ctx.save();
    ctx.fillStyle = this.color;
    ctx.fillRect(x,y,sizeX,sizeY);
    this.parent();
    ctx.restore();
  }
});

});