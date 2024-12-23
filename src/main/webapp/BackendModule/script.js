let users = [
    { id: 1, username: 'user1', password: 'password1', realName: '张三' },
    { id: 2, username: 'user2', password: 'password2', realName: '李四' },
    { id: 3, username: 'user3', password: 'password3', realName: '王五' }
];

function renderUserList() {
    const userList = document.getElementById('userList');
    userList.innerHTML = ''; // Clear the existing list

    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><input type="checkbox" class="userCheckbox" data-id="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.realName}</td>
            <td>
                <button class="edit" onclick="editUser(${user.id})">编辑</button>
                <button class="delete" onclick="deleteUser(${user.id})">删除</button>
            </td>
        `;
        userList.appendChild(row);
    });
}

function toggleSelectAll() {
    const selectAllCheckbox = document.getElementById('selectAll');
    const checkboxes = document.querySelectorAll('.userCheckbox');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
}

function bulkDelete() {
    const selectedUserIds = [];
    const checkboxes = document.querySelectorAll('.userCheckbox:checked');
    checkboxes.forEach(checkbox => {
        selectedUserIds.push(parseInt(checkbox.getAttribute('data-id')));
    });

    if (selectedUserIds.length === 0) {
        alert('请选择要删除的用户!');
        return;
    }

    users = users.filter(user => !selectedUserIds.includes(user.id));
    renderUserList();
}

function editUser(userId) {
    const user = users.find(u => u.id === userId);
    if (user) {
        document.getElementById('formTitle').textContent = '编辑用户';
        document.getElementById('userId').value = user.id;
        document.getElementById('username').value = user.username;
        document.getElementById('password').value = user.password;
        document.getElementById('realName').value = user.realName;
        openPopup();
    }
}

function deleteUser(userId) {
    users = users.filter(user => user.id !== userId);
    renderUserList();
}

function openAddUserPopup() {
    document.getElementById('formTitle').textContent = '添加用户';
    document.getElementById('userForm').reset();
    document.getElementById('userId').value = '';
    openPopup();
}

function openPopup() {
    document.getElementById('userFormPopup').style.display = 'flex';
}

function closePopup() {
    document.getElementById('userFormPopup').style.display = 'none';
}

document.getElementById('userForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userId = parseInt(document.getElementById('userId').value);
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const realName = document.getElementById('realName').value;

    if (userId) {
        // 编辑
        const user = users.find(u => u.id === userId);
        if (user) {
            user.username = username;
            user.password = password;
            user.realName = realName;
        }
    } else {
        // 新增
        const newUser = {
            id: users.length + 1,
            username: username,
            password: password,
            realName: realName
        };
        users.push(newUser);
    }

    renderUserList();
    closePopup();
});

// 初始渲染用户列表
renderUserList();
