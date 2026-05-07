# 🛍️ Product Browser — Android App

A native Android application built with **Java** that fetches live product data from a REST API and displays it in a clean, interactive product list. Features favorites management with a local **Room database**, real-time search, product detail view, and a bottom navigation UI.

---

## ✨ Features

- 🌐 **Live API Data** — Fetches real product data from [FakeStore API](https://fakestoreapi.com/products) on launch
- 📋 **Product List** — Displays title, category, price, image & rating in a RecyclerView
- 🔍 **Real-time Search** — Toggle search bar via FAB and filter products instantly as you type
- ❤️ **Favorites** — Save and remove products to a local Room database
- 📄 **Product Detail** — Tap any product to view full description, rating, and share option
- 🔗 **Share** — Share any product directly from the detail screen
- ⚙️ **Settings Screen** — Dedicated settings activity
- 🌙 **Dark / Light Mode** — Supports system theme automatically
- 🧭 **Bottom Navigation** — Home, Favorites, Settings with smooth screen transitions

---

## 🔁 How It Works

```
App Launch
    ↓
AsyncTask fetches products from FakeStore API
    ↓
JSON parsed → List<Item> created
    ↓
RecyclerView populated with products
    ↓
User taps product → Detail screen
User searches → RecyclerView filtered live
User favorites → Saved to Room DB
User opens Favorites → Loaded from Room DB
```

---

## 🛠️ Tech Stack

| Category        | Technology                              |
|-----------------|-----------------------------------------|
| Language        | Java                                    |
| UI              | XML Layouts, Material Design            |
| Networking      | HttpURLConnection, AsyncTask            |
| Data Parsing    | org.json (JSONArray, JSONObject)        |
| Local Database  | Room (DAO, Entity, RoomDatabase)        |
| List Display    | RecyclerView + Custom Adapters          |
| Navigation      | BottomNavigationView, Intent            |
| Storage         | SharedPreferences                       |
| Build Tool      | Gradle                                  |

---

## 📁 Project Structure

```
Android-main/
│
├── java/com/example/afinal/
│   ├── MainActivity.java          # Home — fetches API, search, navigation
│   ├── favorite.java              # Favorites screen — loads from Room DB
│   ├── setting.java               # Settings screen
│   ├── OnClick_Item.java          # Product detail screen + share
│   ├── Item.java                  # Data model for API products
│   ├── Entity.java                # Room Entity — saved favorites
│   ├── DAO.java                   # Room DAO — insert, delete, getAll
│   ├── Database.java              # Room Database class
│   ├── MyAdapter.java             # RecyclerView adapter with search filter
│   ├── MyAdapter_favorite.java    # RecyclerView adapter for favorites
│   ├── MyViewHolder.java          # ViewHolder for product items
│   └── MyViewHolder_favorite.java # ViewHolder for favorite items
│
└── res/
    ├── layout/                    # XML layouts for all screens
    ├── drawable/                  # Icons (home, favorite, settings, share, delete)
    └── menu/                      # Bottom navigation menu
```

---

## 🗄️ Database Schema (Room)

### Table: `Product` (Favorites)
| Column      | Type    |
|-------------|---------|
| id          | INTEGER (PK, AUTO) |
| title       | TEXT    |
| category    | TEXT    |
| image       | TEXT    |
| price       | TEXT    |
| rate        | TEXT    |
| count       | TEXT    |
| description | TEXT    |

---

## 🌐 API Used

**FakeStore API** — `https://fakestoreapi.com/products`

Returns a list of products with:
- `title`, `category`, `price`, `image`, `description`
- `rating.rate`, `rating.count`

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- Android SDK installed
- Internet connection (for API fetch)

### Run the Project
```bash
# 1. Clone the repository
git clone https://github.com/Hirad1380/Android.git
cd Android

# 2. Open in Android Studio
File → Open → select the project folder

# 3. Run on emulator or physical device
Click ▶ Run
```

---

## 👨‍💻 Author

**Hirad Bayat**  
M.Sc. Applied Computer Science — University of Duisburg-Essen  
📧 Bayathirad7@gmail.com  
