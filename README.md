# ironsworn-companion

This is an unofficial Ironsworn companion app (WIP), written in ClojureScript, using Re-Natal (React-Native).

## Usage

Setup [re-natal](https://github.com/drapanjanas/re-natal) and run

```
re-natal init IronswornCompanion
```

change into `ironsworn-companion` and run

```
git init
git remote add ironsworn-source https://github.com/mschoenherr/ironsworn-companion.git
rm LICENSE README.md src/ironsworn-companion/db.cljs src/ironsworn-companion/subs.cljs src/ironsworn-companion/events.cljs
rm src/ironsworn-companion/android/core.cljs
git fetch ironsworn-source
git merge --allow-unrelated-histories ironsworn-source/master
```

Then, you can work with re-natal as described on [re-natal](https://github.com/drapanjanas/re-natal).

## License

Copyright © 2018 Moritz Schönherr

Distributed under the terms of GPLv3.

All images included were created for the purposes of this software and belong to the Public Domain.

This work is based on Ironsworn (found at www.ironswornrpg.com), created by Shawn Tomkin, and licensed for our use under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International license  (creativecommons.org/licenses/by-nc-sa/4.0/).
