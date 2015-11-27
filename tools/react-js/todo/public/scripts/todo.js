var Todo = React.createClass({
  getInitialState: function() {
    return {tasks: []};
  },
  componentDidMount: function() {
    this.refreshTaskList();
  },
  refreshTaskList: function() {
    this.props.client.getTasks(function(tasks) {
      this.setState({tasks: tasks});
    }.bind(this));
  },
  onTaskDelete: function(taskId) {
    this.props.client.deleteTask(taskId, this.refreshTaskList);
  },
  render: function() {
    return (
      <div>
        <h1>Hacky todo list</h1>
        <TodoList tasks={this.state.tasks}
                  onTaskDelete={this.onTaskDelete}/>
        <TodoEntry />
      </div>
    );
  }
});

var TodoList = React.createClass({
  deleteTask: function(id) {
    this.props.onTaskDelete(id);
  },
  render: function() {
    var taskNodes = this.props.tasks.map(function (task) {
      return (
        <li key={task.id}>
          {task.task}
          (<a onClick={this.deleteTask.bind(this, task.id)}>Hi</a>)
        </li>);
    }.bind(this));
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

