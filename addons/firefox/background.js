browser.pageAction.onClicked.addListener(async (tab) => {
  const cookie = await browser.cookies.get({url: "https://intra.epitech.eu", name: "user"});

  if (cookie) {
    const payload = cookie.value.split(".")[1];
    const decodedPayload = atob(payload);
    const data = JSON.parse(decodedPayload);

    await navigator.clipboard.writeText(`https://apicalendar.pechart.fr/ical/${data.login}`);
    browser.notifications.create({
      "type": "basic",
      "iconUrl": browser.extension.getURL("favicon.ico"),
      "title": "EpiCalendar",
      "message": "Your calendar link has been copied to your clipboard."
    });
  } else {
    browser.notifications.create({
      "type": "basic",
      "iconUrl": browser.extension.getURL("favicon.ico"),
      "title": "EpiCalendar",
      "message": "You need to be connected to the Epitech intranet to use this feature."
    });
  }
});

browser.tabs.onUpdated.addListener((tabId, changeInfo, tab) => {
  if (tab.url.includes("intra.epitech.eu")) {
    browser.pageAction.show(tabId);
  }
});