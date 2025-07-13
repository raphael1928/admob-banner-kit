# 📢 AdMobBannerKit

Uma biblioteca leve e modular para exibir banners do **Google AdMob** em apps Android com **Jetpack Compose** e **XML**, oferecendo suporte ao ciclo de vida do anúncio.

---

## 🧠 Objetivo

Oferecer uma solução plugável e desacoplada para banners de publicidade com:
- Jetpack Compose ou XML
- Controle de ciclo de vida (eventos)
- Suporte a customização do `adUnitId`
- Listener com estados bem definidos (`AdEvent`)
- SDK controlado pelo app consumidor (`AdMobManager.initialize()`)

---

## ✨ Funcionalidades

- ✅ Componente Compose `AdBanner`
- ✅ Componente XML `AdBannerView`
- ✅ Controle de estado por `AdEventListener`
- ✅ Inicialização manual e segura do SDK
- ✅ Testes unitários com JUnit + Mockk
- ✅ Publicada via GitHub Packages

---

## 📦 Instalação

### 1. Configure o repositório do GitHub Packages

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/raphael1928/admob-banner-kit")
            credentials {
                username = "GITHUB_USERNAME"
                password = "GITHUB_TOKEN"
            }
        }
    }
}
> 💡 Substitua `GITHUB_USERNAME` e `GITHUB_TOKEN` por suas credenciais pessoais do GitHub (pode usar um [Token Pessoal](https://github.com/settings/tokens)).

---

### 2. Adicione a dependência

```
kotlin
dependencies {
    implementation("br.com.raphael.admoblib:admoblib:1.0.1")
}
```

## ⚙️ Como usar

### 1. Inicialize o SDK no seu `Application` ou `MainActivity`

```kotlin
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AdMobManager.initialize(this)
    }
}
```

> Essa inicialização é obrigatória e deve ser feita **antes** de qualquer uso de `AdBanner` ou `AdBannerView`.


### 2. Usando com Jetpack Compose

```kotlin
AdBanner(
    adUnitId = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx", // opcional
    adEventListener = AdEventListener { event ->
        when (event) {
            AdEvent.Loading        -> Log.d("AdBanner", "Loading...")
            AdEvent.Loaded         -> Log.d("AdBanner", "Loaded!")
            AdEvent.FailedToLoad   -> Log.e("AdBanner", "Failed!")
            AdEvent.Clicked        -> Log.d("AdBanner", "Clicked")
            AdEvent.Impression     -> Log.d("AdBanner", "Impression")
            AdEvent.Closed         -> Log.d("AdBanner", "Closed")
            AdEvent.Opened         -> Log.d("AdBanner", "Opened")
            AdEvent.NotInitialized -> Log.e("AdBanner", "SDK not initialized")
        }
    }
)
```

---

### 3. Usando com XML

#### XML

```xml
<br.com.raphael.admobbannerkit.ui.legacy.AdBannerView
    android:id="@+id/adBanner"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

#### Compose

```compose
adBanner.setAdUnitId("ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx")
adBanner.setAdEventListener { event ->
    Log.d("AdEvent", event.toString())
}
```

---

## 🧪 Testes Unitários

A biblioteca conta com testes unitários básicos para os principais componentes utilizando **JUnit 4** e **Mockk**.

| Classe               | Testes                                                       |
|---------------------|--------------------------------------------------------------|
| `AdMobManagerTest`  | ✅ `initialize should mark as initialized`                   |
| `AdEventTest`       | ✅ `AdEvent sealed class should have all states`<br>✅ `FailedToLoad should retain error code` |
| `AdEventListenerTest` | ✅ `onEvent should be triggered with correct event`          |

---

## 🗂 Estrutura do projeto

```text
br.com.raphael.admobbannerkit
├── api         // Eventos e listener
├── core        // Gerenciamento de inicialização
└── ui
    ├── AdBanner.kt     // Componente Compose
    └── legacy          // Componente XML
```

---

## 🏷️ Versão atual

**1.0.1**  
Disponível via [GitHub Packages](https://github.com/raphael1928/admob-banner-kit/packages)

---

## 📄 Licença

Distribuído sob a licença MIT.  
© [raphael1928](https://github.com/raphael1928)

