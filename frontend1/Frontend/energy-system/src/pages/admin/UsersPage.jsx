import { useState, useEffect } from 'react';
import { 
    FaSearch, 
    FaPlus, 
    FaDownload, 
    FaUpload, 
    FaEye, 
    FaEdit, 
    FaTrash, 
    FaSignInAlt,
    FaBell,
    FaChevronDown
} from 'react-icons/fa';
import { LuUsers } from 'react-icons/lu';

export default function UsersPage() {
    const [users, setUsers] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [filterStatus, setFilterStatus] = useState('all');
    const [showFilter, setShowFilter] = useState(false);

    // Empty users array - will be populated from API
    useEffect(() => {
        setUsers([]);
    }, []);

    const filteredUsers = users.filter(user => {
        const matchesSearch = user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                            user.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
                            user.mobile.includes(searchTerm);
        const matchesStatus = filterStatus === 'all' || user.status === filterStatus;
        return matchesSearch && matchesStatus;
    });

    const activeUsers = users.filter(user => user.status === 'active').length;
    const totalUsers = users.length;

    const handleView = (userId) => {
        console.log('View user:', userId);
        // Implement view functionality
    };

    const handleEdit = (userId) => {
        console.log('Edit user:', userId);
        // Implement edit functionality
    };

    const handleDelete = (userId) => {
        console.log('Delete user:', userId);
        // Implement delete functionality
    };

    const handleLogin = (userId) => {
        console.log('Login as user:', userId);
        // Implement login functionality
    };

    return (
        <div className="min-h-screen bg-gray-50">
           

            {/* Main Content */}
            <div className="p-6">
                {/* Statistics and Filter */}
                <div className="flex items-center justify-between mb-6">
                    <div className="flex items-center space-x-6">
                        <div className="text-sm text-gray-600">
                            Total members: <span className="font-semibold">0</span>
                        </div>
                        <div className="text-sm text-gray-600">
                            Current used: <span className="font-semibold">0</span>
                        </div>
                    </div>
                    <button 
                        onClick={() => setShowFilter(!showFilter)}
                        className="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700 transition-colors flex items-center space-x-2"
                    >
                        <FaSearch className="text-sm" />
                        <span>Filter</span>
                    </button>
                </div>

                {/* Filter Panel */}
                {showFilter && (
                    <div className="bg-white p-4 rounded-lg shadow-sm border mb-6">
                        <div className="flex items-center space-x-4">
                            <input
                                type="text"
                                placeholder="Search members..."
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                                className="flex-1 px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 focus:border-transparent"
                            />
                            <select
                                value={filterStatus}
                                onChange={(e) => setFilterStatus(e.target.value)}
                                className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 focus:border-transparent"
                            >
                                <option value="all">All Status</option>
                                <option value="active">Active</option>
                                <option value="inactive">Inactive</option>
                            </select>
                        </div>
                    </div>
                )}

                {/* Members Table */}
                <div className="bg-white rounded-lg shadow-sm border overflow-hidden">
                    <div className="px-6 py-4 border-b bg-gray-50">
                        <h2 className="text-lg font-semibold text-gray-900">Members</h2>
                    </div>
                    
                    <div className="overflow-x-auto">
                        <table className="w-full">
                            <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Photo</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Member name</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Mobile</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Operation</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
                                </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                                {filteredUsers.length === 0 ? (
                                    <tr>
                                        <td colSpan="7" className="px-6 py-12 text-center">
                                            <div className="flex flex-col items-center space-y-3">
                                                <LuUsers className="text-gray-400 text-4xl" />
                                                <div className="text-gray-500 text-lg">No members found</div>
                                                <div className="text-gray-400 text-sm">Add your first member to get started</div>
                                            </div>
                                        </td>
                                    </tr>
                                ) : (
                                    filteredUsers.map((user, index) => (
                                        <tr key={user.id} className={index % 2 === 0 ? 'bg-white' : 'bg-gray-50'}>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <img
                                                    src={user.avatar}
                                                    alt={user.name}
                                                    className="w-10 h-10 rounded-full object-cover"
                                                />
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm font-medium text-gray-900">{user.name}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm text-gray-900">{user.mobile}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm text-gray-900">{user.email}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${
                                                    user.status === 'active' 
                                                        ? 'bg-green-100 text-green-800' 
                                                        : 'bg-red-100 text-red-800'
                                                }`}>
                                                    {user.status === 'active' ? 'Active' : 'Inactive'}
                                                </span>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="flex items-center space-x-3">
                                                    <button
                                                        onClick={() => handleView(user.id)}
                                                        className="text-gray-400 hover:text-blue-600 transition-colors"
                                                        title="View"
                                                    >
                                                        <FaEye className="text-lg" />
                                                    </button>
                                                    <button
                                                        onClick={() => handleEdit(user.id)}
                                                        className="text-gray-400 hover:text-yellow-600 transition-colors"
                                                        title="Edit"
                                                    >
                                                        <FaEdit className="text-lg" />
                                                    </button>
                                                    <button
                                                        onClick={() => handleDelete(user.id)}
                                                        className="text-gray-400 hover:text-red-600 transition-colors"
                                                        title="Delete"
                                                    >
                                                        <FaTrash className="text-lg" />
                                                    </button>
                                                </div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <button
                                                    onClick={() => handleLogin(user.id)}
                                                    className="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700 transition-colors flex items-center space-x-2"
                                                >
                                                    <FaSignInAlt className="text-sm" />
                                                    <span>Login</span>
                                                </button>
                                            </td>
                                        </tr>
                                    ))
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>

                {/* Pagination would go here */}
                <div className="mt-6 flex items-center justify-between">
                    <div className="text-sm text-gray-700">
                        Showing 0 of 0 members
                    </div>
                    <div className="flex items-center space-x-2">
                        <button className="px-3 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
                            Previous
                        </button>
                        <button className="px-3 py-2 bg-purple-600 text-white rounded-lg">
                            1
                        </button>
                        <button className="px-3 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
                            2
                        </button>
                        <button className="px-3 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
                            Next
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}