var Todo = React.createClass({
  render: function() {
    return (
      <div>
        <h1>Hacky todo list</h1>
        <TodoList />
        <TodoEntry />
      </div>
    );
  }
});

var TodoList = React.createClass({
  render: function() {
    return (
      <p>Nothing here</p>
    );
  }
});

var TodoEntry = React.createClass({
  render: function() {
    return (
      <p>Nothing here</p>
    );
  }
});

ReactDOM.render(<Todo/>, document.getElementById("content"));
