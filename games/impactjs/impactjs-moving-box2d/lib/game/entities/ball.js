ig.module(
  'game.entities.ball'
)
.requires(
  'impact.entity',
  'plugins.box2d.entity'
)
.defines(function(){

EntityBall = ig.Box2DEntity.extend({
  
  size: { x: 5, y: 5 },  
  restitution: 1,
  friction: 0,

  type: ig.Entity.TYPE.B,
  checkAgainst: ig.Entity.TYPE.NONE,
  collides: ig.Entity.COLLIDES.NEVER,

  color: '#f0f',
  
  init: function( x, y, settings ) {
    // this.addAnim( 'idle', 1, [0] );
    this.parent( x, y, settings );

    this.body.ApplyForce( new b2.Vec2(-40, 80), this.body.GetPosition() );        
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