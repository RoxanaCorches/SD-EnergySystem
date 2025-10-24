import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import AdminPage from "./pages/admin/AdminPage.jsx";
import ClientPage from "./pages/client/ClientPage.jsx";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import AppLayout from "./components/admin/AppLayout.jsx";
import UsersPage from "./pages/admin/UsersPage.jsx";
import DevicesPage from "./pages/admin/DevicesPage.jsx";
import DeviceUser from "./pages/admin/DeviceUser.jsx";
import AppLayoutClient from "./components/client/AppLayoutClient.jsx";
import MyDevices from "./pages/client/MyDevices.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/signUp" element={<SignUpPage />} />

                <Route element={<AppLayout />}>
                    <Route path="/admin" element={<AdminPage />} />
                    <Route path="/users" element={<UsersPage />} />
                    <Route path="/devices" element={<DevicesPage />} />
                    <Route path="/device-user" element={<DeviceUser />} />
                </Route>

                <Route element={<AppLayoutClient />}>
                <Route path="/client" element={<ClientPage />} />
                    <Route path="/myDevices" element={<MyDevices />} />
                </Route>

            </Routes>
        </BrowserRouter>
    );
}
export default App;
