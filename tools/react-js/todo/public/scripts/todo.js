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
  onTaskCreated: function(task) {
    this.props.client.createTask(task, this.refreshTaskList);
  },
  render: function() {
    return (
      <div>
        <h1>Hacky todo list</h1>
        <TodoList tasks={this.state.tasks}
                  onTaskDelete={this.onTaskDelete}/>
        <TodoEntry onTaskCreated={this.onTaskCreated} />
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
          (<a onClick={this.deleteTask.bind(this, task.id)}>Delete</a>)
        </li>);
    }.bind(this));
    return (
      <ul>{taskNodes}</ul>
    );
  }
});

var TodoEntry = React.createClass({
  createNewTask: function(e) {
    e.preventDefault();
    var task = this.refs.task.value.trim();
    if (!task) {
      return;
    }
    this.refs.task.value = '';
    this.props.onTaskCreated({task: task});
  },
  render: function() {
    return (
      <form onSubmit={this.createNewTask}>
        <input type="text" ref="task"/>
        <input type="submit" ref="Create"/>
      </form>
    );
  }
});

var client = new TaskServiceClient("/api/tasks");
ReactDOM.render(<Todo client={client}/>, document.getElementById("content"));

