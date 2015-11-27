var Todo = React.createClass({
  getInitialState: function() {
    return {tasks: []};
  },
  componentDidMount: function() {
    var self = this;
    this.props.client.getTasks(function(tasks) {
      self.setState({tasks: tasks});
    });
  },
  render: function() {
    return (
      <div>
        <h1>Hacky todo list</h1>
        <TodoList tasks={this.state.tasks}/>
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
ReactDOM.render(<Todo client={client}/>, document.getElementById("content"));

