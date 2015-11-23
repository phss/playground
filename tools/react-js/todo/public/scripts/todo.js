var Todo = React.createClass({
  render: function() {
    return (
      <div>
        <h1>Hacky todo list</h1>
        <TodoList tasks={this.props.tasks}/>
        <TodoEntry />
      </div>
    );
  }
});

var TodoList = React.createClass({
  render: function() {
    var taskNodes = this.props.tasks.map(function (task) {
      return (<li key={task.id}>{task.task}</li>);
    });
    return (
      <ul>{taskNodes}</ul>
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

var data = [
  {"id": 1, "task": "Don't hard code data"},
  {"id": 2, "task": "Finish everything"}
];

ReactDOM.render(<Todo tasks={data}/>, document.getElementById("content"));
