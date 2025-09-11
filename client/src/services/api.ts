const API_BASE_URL = 'http://localhost:8080/api';

export interface Project {
  id?: number;
  name: string;
  description: string;
}

export interface Task {
  id?: number;
  name: string;
  description: string;
  status: string;
  projectId: number;
  title: string;
  completed: boolean;
}

export const projectApi = {
    getAllProjects: async (): Promise<Project[]> => {
        const response = await fetch(`${API_BASE_URL}/projects`);
        if (!response.ok) {
            throw new Error('Failed to fetch projects');
        }
        return response.json();
    },

    getProjectById: async (id: number): Promise<Project> => {
        const response = await fetch(`${API_BASE_URL}/projects/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch project with id ${id}`);
        }
        return response.json();
    },

    createProject: async (project: Project): Promise<Project> => {
        const response = await fetch(`${API_BASE_URL}/projects`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(project),
        });
        if (!response.ok) {
            throw new Error('Failed to create project');
        }
        return response.json();
    },

    updateProject: async (id: number, project: Project): Promise<Project> => {
        const response = await fetch(`${API_BASE_URL}/projects/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(project),
        });
        if (!response.ok) {
            throw new Error(`Failed to update project with id ${id}`);
        }
        return response.json();
    },
    
    deleteProject: async (id: number): Promise<void> => {
        const response = await fetch(`${API_BASE_URL}/projects/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error(`Failed to delete project with id ${id}`);
        }
    },

};

export const taskApi = {
    getAllTasks: async (): Promise<Task[]> => {
        const response = await fetch(`${API_BASE_URL}/tasks`);
        if (!response.ok) {
            throw new Error('Failed to fetch tasks');
        }
        return response.json();
    },
    
    getTaskById: async (id: number): Promise<Task> => {
        const response = await fetch(`${API_BASE_URL}/tasks/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch task with id ${id}`);
        }
        return response.json();
    },
    
    getTasksByProjectId: async (projectId: number): Promise<Task[]> => {
        const response = await fetch(`${API_BASE_URL}/projects/${projectId}/tasks`);
        if (!response.ok) {
            throw new Error(`Failed to fetch tasks for project with id ${projectId}`);
        }
        return response.json();
    },

    createTask: async (task: Task): Promise<Task> => {
        const response = await fetch(`${API_BASE_URL}/tasks`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(task),
        });
        if (!response.ok) {
            throw new Error('Failed to create task');
        }
        return response.json();
    },
    
    updateTask: async (id: number, task: Task): Promise<Task> => {
        const response = await fetch(`${API_BASE_URL}/tasks/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(task),
        });
        if (!response.ok) {
            throw new Error(`Failed to update task with id ${id}`);
        }
        return response.json();
    },
    
    deleteTask: async (id: number): Promise<void> => {
        const response = await fetch(`${API_BASE_URL}/tasks/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error(`Failed to delete task with id ${id}`);
        }
    },
};