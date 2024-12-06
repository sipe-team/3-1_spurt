db.createUser(
    {
        user: "prefer",
        pwd: "prefer",
        roles: [
            {
                role: "readWrite",
                db: "display"
            }
        ]
    }
);
