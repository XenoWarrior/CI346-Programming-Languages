import React from 'react';
import {render} from 'react-dom';

var EmployeeClient = {
	deleteEmployee: function(id) {
    	$.ajax({
    		method: "DELETE",
    		url: "./api/employee/" + id,
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			$('#error-modal').modal('open');
    			$("#error-text").text(response['error']);
    		}
    		else {
    			render(<App />,	document.getElementById('employee-target'));
    		}
    	});
	},
	editEmployee: function() {
		
	},
	debugUndeleteAll: function() {
    	$.ajax({
    		method: "GET",
    		url: "./api/debug/undeleteall",
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			$('#error-modal').modal('open');
    			$("#error-text").text(response['error']);
    		}
    		else {
    			render(<App />,	document.getElementById('employee-target'));
    		}
    	});
	},
	debugDeleteAll: function() {
    	$.ajax({
    		method: "DELETE",
    		url: "./api/debug/deleteall",
    	}).done(function(msg) {
    		var response = JSON.parse(msg);
    		console.log(response);

    		if(typeof response['error'] != "undefined") { 
    			$('#error-modal').modal('open');
    			$("#error-text").text(response['error']);
    		}
    		else {
    			render(<App />,	document.getElementById('employee-target'));
    		}
    	});
	}
}

class Employee extends React.Component {
	render() {
		console.log("Employee::render()");
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

class EmployeeList extends React.Component {
	render() {
		console.log("EmployeeList::render()");
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

class ErrorComponent extends React.Component {
	render() {
		return(<p>An error occured.</p>);
	}
}

class Debug extends React.Component {
	render() {
		return(
			<table>
				<thead>
					<tr>
						<th>Action</th>
						<th>Button</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Undelete All Employees</td>
						<td><a onClick={EmployeeClient.debugUndeleteAll.bind(this)} className="waves-effect waves-light btn">Go</a></td>
					</tr>
					<tr>
						<td>Delete All Employees</td>
						<td><a onClick={EmployeeClient.debugDeleteAll.bind(this)} className="waves-effect waves-light btn">Go</a></td>
					</tr>
				</tbody>
			</table>
		);
	}
}

class App extends React.Component {
    constructor(props) {
		console.log("App::constructor(props)");
        super(props);
    }
    
    render() {
		console.log("App::render()");
		
    	var employeeList = {};
    	var finalResult = React.Component();
    	
    	$.ajax({
    		method: "GET",
            async: false,
    		url: "./api/employees",
    	}).done(function(msg) {
        	console.log("Done fetching EmployeeList");
    		employeeList = JSON.parse(msg);
    		console.log(employeeList);

    		if(typeof employeeList['error'] != "undefined") { 
    			employeeList = {};
    			
    			$('#error-modal').modal('open');
    			$("#error-text").text(employeeList['error']);
    			
            	console.log("Returning ErrorComponent");
            	finalResult = <ErrorComponent />;
    		}

        	console.log("Returning EmployeeList");
            finalResult = <EmployeeList employeeList = {employeeList}/>;
    	});
        
    	console.log(finalResult);
        return(finalResult);
    }
}

render(<App />, document.getElementById('employee-target'));
render(<Debug />, document.getElementById('debug-target'));
