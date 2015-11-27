var TaskServiceClient = function(serviceUrl) {
  this.serviceUrl = serviceUrl;
};

TaskServiceClient.prototype.getTasks = function(callback) {
  $.get(this.serviceUrl, callback);
};

TaskServiceClient.prototype.deleteTask = function(taskId, callback) {
  $.ajax({
    url: this.serviceUrl + "/" + taskId,
    type: 'DELETE',
    success: callback});
};

TaskServiceClient.prototype.createTask = function(task, callback) {
  $.ajax({
    url: this.serviceUrl + "/new",
    type: 'POST',
    dataType: 'json',
    data: JSON.stringify(task),
    success: callback});
};
