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

var client = new TaskServiceClient("/api/tasks");

client.getTasks(function(data) {
  ReactDOM.render(<Todo tasks={data}/>, document.getElementById("content"));
});

