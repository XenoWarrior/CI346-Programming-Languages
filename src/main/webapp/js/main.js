import React from 'react';
import {render} from 'react-dom';

/**
 * EmployeeClient (Object)
 * 
 * Standard object which contains methods that talk with the API after the page has loaded.
 */
var EmployeeClient = {
	deleteEmployee: function(id) {
    	$.ajax({
    		method: "DELETE",
    		url: "./api/employee/" + id,
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		
    		console.log("EmployeeClient::deleteEmployee() response:")
    		console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			render(<Error title={"Error"} message={response['error']} />, document.getElementById('employee-target'));
    		}
    		else {
    			render(<App />, document.getElementById('employee-target'));
    		}
    	});
	},
	editEmployee: function() {
		
	}
}

/**
 * DebugClient (Object)
 * 
 * Standard object similar to EmployeeClient, used to make developing the website all that much easier. 
 */
var DebugClient = {

	// Used for undeleting all records in the database.
	// Method: GET
	debugUndeleteAll: function() {
    	$.ajax({
    		method: "GET",
    		url: "./api/debug/undeleteall",
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		//console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			render(<Error title={"Error"} message={response['error']} />, document.getElementById('employee-target'));
    		}
    		else {
    			render(<App />, document.getElementById('employee-target'));
    		}
    	});
	},

	// Used for deleting all records in the database.
	// Method: DELETE
	debugDeleteAll: function() {
    	$.ajax({
    		method: "DELETE",
    		url: "./api/debug/deleteall",
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		//console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			$('#error-modal').modal('open');
    			$("#error-text").text(response['error']);
    		}
    		else {
    			render(<App />, document.getElementById('employee-target'));
    		}
    	});
	},
	
	// Used to reload the document without refreshing the page.
	rerenderDocument: function() { 
		render(<App />, document.getElementById('employee-target'));
		render(<Debug />, document.getElementById('debug-target'));
	}
}

/**
 * Employee (React.Component)
 * 
 * The React.Component for each individual employee in the EmployeeList React.Component 
 */
class Employee extends React.Component {
	render() {
		console.log("Employee::render() props:");
		console.log(this.props);
		return (
			<tr id={"employee-"+this.props.employeeObject['id']}>
				<td>{this.props.employeeObject['id']}</td>
				<td>{this.props.employeeObject['fullname']}</td>
				<td>{this.props.employeeObject['shift']}</td>
				<td className="center-align">
					<div>
						<a onClick={ EmployeeClient.deleteEmployee.bind(this, this.props.employeeObject['id']) } className="space waves-effect waves-light btn">Delete</a>
						<a onClick={ EmployeeClient.editEmployee.bind(this) } className="waves-effect waves-light btn">Edit</a>
					</div>
				</td>
			</tr>
		);
	}
}

/**
 * EmployeeList (React.Component)
 * 
 * The React.Component which contains all the employees in a table, appends Employee React.Component in {employeeList}.
 */
class EmployeeList extends React.Component {
	render() {
		console.log("EmployeeList::render() employeeList:");
		console.log(this.props.employeeList);
		
		var employeeList = Object.values(this.props.employeeList).map(employeeObject =>
			<Employee employeeObject = {JSON.parse(employeeObject)}/>
		);

		return(
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Shift</th>
						<th className="center-align">Actions</th>
					</tr>
				</thead>
				<tbody>
					{employeeList}
				</tbody>
			</table>
		);
	}
}

/**
 * Error (React.Component)
 * 
 * The React.Component which pushes errors to the screen.
 */
class Error extends React.Component {
	render() {
		console.log("Error::render() props:");
		console.log(this.props);
		
		return(
			<div className="row">
			    <div className="col s12">
			        <div className="card blue-grey darken-1">
			            <div className="card-content white-text">
			                <span className="card-title">{this.props.title}</span>
			                <p>{this.props.message}</p>
			            </div>
			            <div className="card-action">
			                <a href="./">Reload</a>
			            </div>
			        </div>
		        </div>
	        </div>
		);
	}
}

/**
 * Debug (React.Component)
 * 
 * The React.Component for pushing debug controls to screen.
 * Can be disabled at the bottom of this file by removing the render(<Debug />, {...});
 */
class Debug extends React.Component {
	render() {
		//console.log("Debug::render()");
		
		return(
			<table>
				<thead>
					<tr>
						<th>Action</th>
						<th className="center-align">Button</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Undelete All Employees</td>
						<td className="center-align"><a onClick={DebugClient.debugUndeleteAll.bind(this)} className="waves-effect waves-light btn">Go</a></td>
					</tr>
					<tr>
						<td>Delete All Employees</td>
						<td className="center-align"><a onClick={DebugClient.debugDeleteAll.bind(this)} className="waves-effect waves-light btn">Go</a></td>
					</tr>
					<tr>
						<td>Rerender Document</td>
						<td className="center-align"><a onClick={DebugClient.rerenderDocument.bind(this)} className="waves-effect waves-light btn">Go</a></td>
					</tr>
				</tbody>
			</table>
		);
	}
}

/**
 * App (React.Component)
 * 
 * The React.Component for entry into the web application.
 * This component sets up the initial employee list or displays an error on failure.
 * It will attempt to reconnect upon error.
 */
class App extends React.Component {
    constructor(props) {
		console.log("App::constructor(props)");
        super(props);
    }

    componentDidMount() {
        var response = {};
        
        // Used to recall this method if there is a failure.
        var _this = this;
        
    	$.ajax({
    		method: "GET",
            async: true,
    		url: "./api/employees",
    	}).done(function(msg) {
    		response = JSON.parse(msg);
    		
    		console.log("App::componentDidMount() ajax.done() response:");
    		console.log(response);

    		if(typeof response['error'] != "undefined") {
        		// Checking for error, if this is the case, display an error on the screen.
    			console.log("Response error, pushing error to screen.");
    			
            	var errorMessageComponent = <Error title={"Error"} message={response['error'] + " Retrying..."} />;
    			render(errorMessageComponent, document.getElementById('employee-target'));

    			console.log("Attempting reconnection...");
            	_this.componentDidMount();
    		}
    		else {
        		// Checking for undefined error, if this is the case, no error actually occurred.
    			console.log("Response success, setting up employee list...");
    	    	var employeeListComponent = <EmployeeList employeeList={response} />;
    			
    	    	// Render the new employee list.
    	    	console.log("Rendering...");
    			render(employeeListComponent, document.getElementById('employee-target'));

    			// Cache employee list for potential refresh later.
    			console.log("Caching employeeList in DataStorage:");
    			DataStorage['hasLoaded'] = true;
        		DataStorage['lastEmployeeList'] = employeeListComponent;
    			console.log(DataStorage);
    		}
    	});
    }
    
    render() {
    	//console.log("App::render()");
    	
    	// Check if we've loaded.
    	// Continue to slow last employee list if already loaded to prevent the screen from flickering the loading message.
    	if(DataStorage['hasLoaded']) {
    		console.log("Setting last employee list while waiting for new list...");
    		return DataStorage['lastEmployeeList'];
    	}
    	
    	// For the first load, show a message.
    	// Will vanish quick if server responds quickly.
		console.log("Setting first time load message...");
    	console.log(DataStorage['notLoadedState']);
		return DataStorage['notLoadedState'];
    }
}

/**
 * DataStorage (Object)
 * 
 * Used to keep some values cached such as a previous employee list 
 * for when the user performs an action that updates the page, preventing it
 * from jumping them back to the top of the screen.
 */
var DataStorage = {
		hasLoaded: false, 
		lastEmployeeState: 0,
		notLoadedState: <Error title={"Loading"} message={"Fetching employee list..."} />
}

render(<App />, document.getElementById('employee-target'));
render(<Debug />, document.getElementById('debug-target'));
