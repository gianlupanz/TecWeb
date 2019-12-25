<b>TecWeb project realized with Angelo Borrelli</b>

Development of a web application for the shared development of documents and their
publication. A group of people work on developing a document, with a check
version accuracy: you must keep the history of the versions with the author of each change, in
so you can revert to one of the previous versions at any time. Every author must be able
work independently on the client, and then update the version on the server. In that case, it is necessary that
only the part of the document he was working on is changed, in order to avoid any
conflicts. However, if someone has modified the parts on which the author has worked, it must be reported in
so that it can resolve the conflict. The document can only be published when all authors
they gave their OK to the final version. When one of the authors requests publications, such
request is also reported to others. In the public part of the site it must be possible
access documents published by title, date and author. Where appropriate, a
controller may block or permanently delete documents and / or block for a period or
definitely one of the authors.
